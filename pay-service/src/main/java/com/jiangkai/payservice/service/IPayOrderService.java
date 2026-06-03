package com.jiangkai.payservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jiangkai.payservice.domain.dto.PayApplyDTO;
import com.jiangkai.payservice.domain.dto.PayOrderFormDTO;
import com.jiangkai.payservice.domain.po.PayOrder;

/**
 * <p>
 * 支付订单 服务类
 * </p>
 *
 * @author 虎哥
 * @since 2023-05-16
 */
public interface IPayOrderService extends IService<PayOrder> {

    String applyPayOrder(PayApplyDTO applyDTO);

    void tryPayOrderByBalance(PayOrderFormDTO payOrderFormDTO);
}
