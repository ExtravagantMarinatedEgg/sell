<!DOCTYPE html>
<html>

<#include "../common/header.ftl">

<body>

<div id="wrapper" class="toggled">

    <#--边栏sidebar-->
    <#include "../common/nav.ftl">

    <#--主要内容content-->
    <div id="page-content-wrapper">

        <div class="container-fluid">

            <div class="row clearfix">
                <div class="col-md-5 column">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>
                                订单id
                            </th>
                            <th>
                                订单总金额
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>
                                ${orderDTO.orderId}
                            </td>
                            <td>
                                ${orderDTO.orderAmount}
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <#--订单详情数据表-->
                <div class="col-md-12 column">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>
                                商品id
                            </th>
                            <th>
                                商品名称
                            </th>
                            <th>
                                价格
                            </th>
                            <th>
                                数量
                            </th>
                            <th>
                                总额
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list orderDTO.orderDetailList as orderDetail>
                            <tr class="info">
                                <td>
                                    ${orderDetail.productId}
                                </td>
                                <td>
                                    ${orderDetail.productName}
                                </td>
                                <td>
                                    ${orderDetail.productPrice}
                                </td>
                                <td>
                                    ${orderDetail.productQuantity}
                                </td>
                                <td>
                                    ${orderDetail.productQuantity * orderDetail.productPrice}
                                </td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>

                <#if orderDTO.getOrderStatus() == 0>
                    <div class="col-md-12 column">
                        <a href="/sell/seller/order/finish?orderId=${orderDTO.orderId}" type="button"
                           class="btn btn-primary btn-default">完结订单</a>
                        <a href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}" type="button"
                           class="btn btn-default btn-danger">取消订单</a>
                    </div>
                </#if>

            </div>
        </div>
    </div>
</div>


</body>
</html>