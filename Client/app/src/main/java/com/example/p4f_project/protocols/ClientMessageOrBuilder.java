// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: PATH/MessageWrapper.proto

package com.example.p4f_project.protocols;

public interface ClientMessageOrBuilder extends
    // @@protoc_insertion_point(interface_extends:p4f_protocols.ClientMessage)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 opcode = 1;</code>
   * @return The opcode.
   */
  int getOpcode();

  /**
   * <pre>
   *Use for login and change password
   * </pre>
   *
   * <code>.p4f_protocols.LoginInfo account = 2;</code>
   * @return Whether the account field is set.
   */
  boolean hasAccount();
  /**
   * <pre>
   *Use for login and change password
   * </pre>
   *
   * <code>.p4f_protocols.LoginInfo account = 2;</code>
   * @return The account.
   */
  com.example.p4f_project.protocols.LoginInfo getAccount();
  /**
   * <pre>
   *Use for login and change password
   * </pre>
   *
   * <code>.p4f_protocols.LoginInfo account = 2;</code>
   */
  com.example.p4f_project.protocols.LoginInfoOrBuilder getAccountOrBuilder();

  /**
   * <code>.p4f_protocols.RegisterInfo regAcc = 3;</code>
   * @return Whether the regAcc field is set.
   */
  boolean hasRegAcc();
  /**
   * <code>.p4f_protocols.RegisterInfo regAcc = 3;</code>
   * @return The regAcc.
   */
  com.example.p4f_project.protocols.RegisterInfo getRegAcc();
  /**
   * <code>.p4f_protocols.RegisterInfo regAcc = 3;</code>
   */
  com.example.p4f_project.protocols.RegisterInfoOrBuilder getRegAccOrBuilder();

  /**
   * <code>.p4f_protocols.Order order = 4;</code>
   * @return Whether the order field is set.
   */
  boolean hasOrder();
  /**
   * <code>.p4f_protocols.Order order = 4;</code>
   * @return The order.
   */
  com.example.p4f_project.protocols.Order getOrder();
  /**
   * <code>.p4f_protocols.Order order = 4;</code>
   */
  com.example.p4f_project.protocols.OrderOrBuilder getOrderOrBuilder();

  /**
   * <code>.p4f_protocols.changePassInfo changeRes = 5;</code>
   * @return Whether the changeRes field is set.
   */
  boolean hasChangeRes();
  /**
   * <code>.p4f_protocols.changePassInfo changeRes = 5;</code>
   * @return The changeRes.
   */
  com.example.p4f_project.protocols.changePassInfo getChangeRes();
  /**
   * <code>.p4f_protocols.changePassInfo changeRes = 5;</code>
   */
  com.example.p4f_project.protocols.changePassInfoOrBuilder getChangeResOrBuilder();

  public com.example.p4f_project.protocols.ClientMessage.ClientRequestsCase getClientRequestsCase();
}
