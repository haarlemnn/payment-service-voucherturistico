package com.voucherturistico.payment.infrastructure.http.bb.mappers;

import com.voucherturistico.payment.domain.enums.PaymentMethodType;
import com.voucherturistico.payment.domain.enums.PaymentStatus;
import com.voucherturistico.payment.domain.enums.PaymentType;
import com.voucherturistico.payment.domain.models.PaymentRefund;
import com.voucherturistico.payment.domain.models.PixPayment;
import com.voucherturistico.payment.domain.models.response.PaymentStatusResponse;
import com.voucherturistico.payment.infrastructure.http.bb.models.enums.PixStatus;
import com.voucherturistico.payment.infrastructure.http.bb.models.pix.response.PixCreatedResponse;
import com.voucherturistico.payment.infrastructure.http.bb.models.pix.response.PixDetailsResponse;
import com.voucherturistico.payment.infrastructure.http.bb.models.pix.response.PixTransactionCancelledResponse;
import com.voucherturistico.payment.infrastructure.http.bb.models.pixv2.PixRefund;
import com.voucherturistico.payment.infrastructure.http.bb.models.pixv2.PixTransaction;
import com.voucherturistico.payment.infrastructure.http.bb.models.pixv2.response.PixDetailsV2Response;
import com.voucherturistico.payment.infrastructure.http.bb.models.pixv2.response.PixRefundResponse;
import io.micrometer.common.util.StringUtils;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PixDetailsMapper {

    private static PaymentStatus getPaymentStatus(PixStatus pixStatus) {
        switch (pixStatus) {
            case ATIVA -> {
                return PaymentStatus.PENDING;
            }
            case EM_PROCESSAMENTO -> {
                return PaymentStatus.PROCESSING;
            }
            case CONCLUIDA -> {
                return PaymentStatus.SUCCESS;
            }
            case NAO_REALIZADO -> {
                return PaymentStatus.REJECTED;
            }
            case DEVOLVIDO -> {
                return PaymentStatus.REFUNDED;
            }
            case REMOVIDA_PELO_USUARIO_RECEBEDOR, REMOVIDA_PELO_PSP -> {
                return PaymentStatus.CANCELLED;
            }
        }

        return PaymentStatus.FAILURE;
    }

    /**
     * Used by API Pix V2
     * @param pixDetailsResponse PixDetailsV2Response
     * @return PaymentStatusResponse
     */
    public static PaymentStatusResponse fromPixV2ToPaymentStatusResponse(PixDetailsV2Response pixDetailsResponse) {
        PaymentStatusResponse paymentStatusResponse = new PaymentStatusResponse();

        PixPayment payment = new PixPayment();
        payment.setTransactionId(pixDetailsResponse.getTxid());
        payment.setPaymentType(PaymentType.BB);
        payment.setPaymentMethod(PaymentMethodType.PIX_V2);
        payment.setStatus(getPaymentStatus(pixDetailsResponse.getStatus()));

        // QR Code
        payment.setQrCode(pixDetailsResponse.getPixCopiaECola());
        payment.setQrCodeUrl(pixDetailsResponse.getLocation());

        // Payment details
        if (!CollectionUtils.isEmpty(pixDetailsResponse.getPix())) {
            PixTransaction pixTransaction = pixDetailsResponse.getPix().get(0);

            payment.setPaymentId(pixTransaction.getEndToEndId());
            payment.setAmount(new BigDecimal(pixTransaction.getValor()));

            if (!CollectionUtils.isEmpty(pixTransaction.getDevolucoes())) {
                List<PaymentRefund> paymentRefunds = new ArrayList<>();
                BigDecimal refundedAmount = BigDecimal.ZERO;

                for (PixRefund pixRefund : pixTransaction.getDevolucoes()) {
                    BigDecimal amount = new BigDecimal(pixRefund.getValor());

                    PaymentRefund paymentRefund = new PaymentRefund();
                    paymentRefund.setAmount(amount);
                    paymentRefund.setRefundTransactionId(pixRefund.getId());

                    refundedAmount = refundedAmount.add(amount);
                    paymentRefunds.add(paymentRefund);
                }

                if (refundedAmount.compareTo(payment.getAmount()) < 0) {
                    paymentStatusResponse.setStatus(PaymentStatus.PARTIAL_REFUNDED);
                } else {
                    paymentStatusResponse.setStatus(PaymentStatus.REFUNDED);
                }

                paymentStatusResponse.setPaymentRefund(paymentRefunds);
            }
        } else {
            payment.setAmount(new BigDecimal(pixDetailsResponse.getValor().getOriginal()));
            paymentStatusResponse.setStatus(getPaymentStatus(pixDetailsResponse.getStatus()));
        }

        paymentStatusResponse.setPayment(payment);

        return paymentStatusResponse;
    }

    /**
     * Used by API Pix V2
     * @param pixRefundResponse PixRefundResponse
     * @return PaymentStatusResponse
     */
    public static PaymentStatusResponse fromPixRefundToPaymentStatusResponse(PixRefundResponse pixRefundResponse) {
        PixPayment payment = new PixPayment();
        payment.setTransactionId(pixRefundResponse.getId());
        payment.setPaymentType(PaymentType.BB);
        payment.setPaymentMethod(PaymentMethodType.PIX_V2);
        payment.setStatus(getPaymentStatus(pixRefundResponse.getStatus()));
        payment.setAmount(new BigDecimal(pixRefundResponse.getValor()));

        PaymentStatusResponse paymentStatusResponse = new PaymentStatusResponse();
        paymentStatusResponse.setPayment(payment);

        return paymentStatusResponse;
    }

    /**
     * Used by API Pix v1
     * @param transactionId String
     * @param pixDetailsResponse PixDetailsResponse
     * @return PaymentStatusResponse
     */
    public static PaymentStatusResponse fromPixToPaymentStatusResponse(String transactionId, PixDetailsResponse pixDetailsResponse) {
        PixPayment payment = new PixPayment();
        payment.setTransactionId(transactionId);
        payment.setPaymentType(PaymentType.BB);
        payment.setPaymentMethod(PaymentMethodType.PIX);
        payment.setStatus(getPaymentStatus(pixDetailsResponse.getEstadoSolicitacao()));

        // Payment details
        if (StringUtils.isNotBlank(pixDetailsResponse.getCodigoIdentificadorPagamento())) {
            payment.setPaymentId(pixDetailsResponse.getCodigoIdentificadorPagamento());
        }

        if (pixDetailsResponse.getValorPagamento().compareTo(BigDecimal.ZERO) > 0) {
            payment.setAmount(pixDetailsResponse.getValorPagamento());
        } else {
            payment.setAmount(pixDetailsResponse.getValorOriginalSolicitacao());
        }

        // QR Code
        payment.setQrCode(pixDetailsResponse.getQrCode());
        payment.setQrCodeUrl(pixDetailsResponse.getLinkQrCode());

        PaymentStatusResponse paymentStatusResponse = new PaymentStatusResponse();
        paymentStatusResponse.setPayment(payment);
        paymentStatusResponse.setStatus(getPaymentStatus(pixDetailsResponse.getEstadoSolicitacao()));

        return paymentStatusResponse;
    }

    /**
     * Used by API Pix V1
     * @param pixCreatedResponse PixCreatedResponse
     * @param paymentAmount BigDecimal
     * @return PaymentStatusResponse
     */
    public static PaymentStatusResponse fromPixCreatedToPaymentStatusResponse(PixCreatedResponse pixCreatedResponse, BigDecimal paymentAmount) {
        PixPayment payment = new PixPayment();
        payment.setTransactionId(pixCreatedResponse.getCodigoConciliacaoSolicitante());
        payment.setPaymentType(PaymentType.BB);
        payment.setPaymentMethod(PaymentMethodType.PIX);
        payment.setStatus(getPaymentStatus(pixCreatedResponse.getEstadoSolicitacao()));
        payment.setAmount(paymentAmount);

        // QR Code
        payment.setQrCode(pixCreatedResponse.getQrCode());
        payment.setQrCodeUrl(pixCreatedResponse.getLinkQrCode());

        PaymentStatusResponse paymentStatusResponse = new PaymentStatusResponse();
        paymentStatusResponse.setPayment(payment);

        return paymentStatusResponse;
    }

    /**
     * Used by API Pix V1
     * @param transactionId String
     * @param pixTransactionCancelledResponse PixTransactionCancelledResponse
     * @return PaymentStatusResponse
     */
    public static PaymentStatusResponse fromPixCancelledToPaymentStatusResponse(String transactionId, PixTransactionCancelledResponse pixTransactionCancelledResponse) {
        PixPayment payment = new PixPayment();
        payment.setTransactionId(transactionId);
        payment.setPaymentType(PaymentType.BB);
        payment.setPaymentMethod(PaymentMethodType.PIX);
        payment.setStatus(getPaymentStatus(pixTransactionCancelledResponse.getEstadoSolicitacao()));

        PaymentStatusResponse paymentStatusResponse = new PaymentStatusResponse();
        paymentStatusResponse.setPayment(payment);

        return paymentStatusResponse;
    }

}
