// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: PATH/MessageWrapper.proto

package com.p2p.p4f.protocols;

public final class MessageWrapper {
  private MessageWrapper() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_p4f_protocols_LoginInfo_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_p4f_protocols_LoginInfo_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_p4f_protocols_UserAccount_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_p4f_protocols_UserAccount_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_p4f_protocols_RegisterInfo_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_p4f_protocols_RegisterInfo_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_p4f_protocols_changePassInfo_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_p4f_protocols_changePassInfo_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_p4f_protocols_Food_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_p4f_protocols_Food_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_p4f_protocols_orderResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_p4f_protocols_orderResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_p4f_protocols_Order_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_p4f_protocols_Order_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_p4f_protocols_InfoResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_p4f_protocols_InfoResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_p4f_protocols_ClientMessage_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_p4f_protocols_ClientMessage_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_p4f_protocols_ServerMessage_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_p4f_protocols_ServerMessage_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\031PATH/MessageWrapper.proto\022\rp4f_protoco" +
      "ls\"/\n\tLoginInfo\022\020\n\010username\030\001 \001(\t\022\020\n\010pas" +
      "sword\030\002 \001(\t\"\\\n\013UserAccount\022\020\n\010username\030\001" +
      " \001(\t\022\014\n\004type\030\002 \001(\005\022\r\n\005email\030\003 \001(\t\022\r\n\005pho" +
      "ne\030\004 \001(\t\022\017\n\007address\030\005 \001(\t\"a\n\014RegisterInf" +
      "o\022\020\n\010username\030\001 \001(\t\022\020\n\010password\030\002 \001(\t\022\r\n" +
      "\005email\030\003 \001(\t\022\r\n\005phone\030\004 \001(\t\022\017\n\007address\030\005" +
      " \001(\t\"D\n\016changePassInfo\022\020\n\010username\030\001 \001(\t" +
      "\022\017\n\007oldPass\030\002 \001(\t\022\017\n\007newPass\030\003 \001(\t\"s\n\004Fo" +
      "od\022\016\n\006foodID\030\001 \001(\t\022\020\n\003des\030\002 \001(\tH\000\210\001\001\022\025\n\010" +
      "foodName\030\003 \001(\tH\001\210\001\001\022\r\n\005price\030\004 \001(\005\022\016\n\006am" +
      "ount\030\005 \001(\005B\006\n\004_desB\013\n\t_foodName\"F\n\rorder" +
      "Response\022\024\n\007orderID\030\001 \001(\tH\000\210\001\001\022\023\n\013orderR" +
      "esult\030\002 \001(\005B\n\n\010_orderID\"`\n\005Order\022\020\n\010user" +
      "name\030\001 \001(\t\022\017\n\007buyDate\030\002 \001(\t\022\r\n\005resID\030\003 \001" +
      "(\t\022%\n\010foodList\030\004 \003(\0132\023.p4f_protocols.Foo" +
      "d\"L\n\014InfoResponse\022\016\n\006reCode\030\001 \001(\005\022,\n\010use" +
      "rInfo\030\002 \001(\0132\032.p4f_protocols.UserAccount\"" +
      "\351\001\n\rClientMessage\022\016\n\006opcode\030\001 \001(\005\022+\n\007acc" +
      "ount\030\002 \001(\0132\030.p4f_protocols.LoginInfoH\000\022-" +
      "\n\006regAcc\030\003 \001(\0132\033.p4f_protocols.RegisterI" +
      "nfoH\000\022%\n\005order\030\004 \001(\0132\024.p4f_protocols.Ord" +
      "erH\000\0222\n\tchangeRes\030\005 \001(\0132\035.p4f_protocols." +
      "changePassInfoH\000B\021\n\017client_requests\"\346\001\n\r" +
      "ServerMessage\022\016\n\006opcode\030\001 \001(\005\0223\n\014infoRes" +
      "ponse\030\002 \001(\0132\033.p4f_protocols.InfoResponse" +
      "H\000\022\026\n\014responseCode\030\003 \001(\005H\000\0220\n\010orderRes\030\004" +
      " \001(\0132\034.p4f_protocols.orderResponseH\000\0222\n\t" +
      "changeRes\030\005 \001(\0132\035.p4f_protocols.changePa" +
      "ssInfoH\000B\022\n\020server_responsesB\031\n\025com.p2p." +
      "p4f.protocolsP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_p4f_protocols_LoginInfo_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_p4f_protocols_LoginInfo_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_p4f_protocols_LoginInfo_descriptor,
        new java.lang.String[] { "Username", "Password", });
    internal_static_p4f_protocols_UserAccount_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_p4f_protocols_UserAccount_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_p4f_protocols_UserAccount_descriptor,
        new java.lang.String[] { "Username", "Type", "Email", "Phone", "Address", });
    internal_static_p4f_protocols_RegisterInfo_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_p4f_protocols_RegisterInfo_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_p4f_protocols_RegisterInfo_descriptor,
        new java.lang.String[] { "Username", "Password", "Email", "Phone", "Address", });
    internal_static_p4f_protocols_changePassInfo_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_p4f_protocols_changePassInfo_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_p4f_protocols_changePassInfo_descriptor,
        new java.lang.String[] { "Username", "OldPass", "NewPass", });
    internal_static_p4f_protocols_Food_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_p4f_protocols_Food_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_p4f_protocols_Food_descriptor,
        new java.lang.String[] { "FoodID", "Des", "FoodName", "Price", "Amount", "Des", "FoodName", });
    internal_static_p4f_protocols_orderResponse_descriptor =
      getDescriptor().getMessageTypes().get(5);
    internal_static_p4f_protocols_orderResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_p4f_protocols_orderResponse_descriptor,
        new java.lang.String[] { "OrderID", "OrderResult", "OrderID", });
    internal_static_p4f_protocols_Order_descriptor =
      getDescriptor().getMessageTypes().get(6);
    internal_static_p4f_protocols_Order_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_p4f_protocols_Order_descriptor,
        new java.lang.String[] { "Username", "BuyDate", "ResID", "FoodList", });
    internal_static_p4f_protocols_InfoResponse_descriptor =
      getDescriptor().getMessageTypes().get(7);
    internal_static_p4f_protocols_InfoResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_p4f_protocols_InfoResponse_descriptor,
        new java.lang.String[] { "ReCode", "UserInfo", });
    internal_static_p4f_protocols_ClientMessage_descriptor =
      getDescriptor().getMessageTypes().get(8);
    internal_static_p4f_protocols_ClientMessage_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_p4f_protocols_ClientMessage_descriptor,
        new java.lang.String[] { "Opcode", "Account", "RegAcc", "Order", "ChangeRes", "ClientRequests", });
    internal_static_p4f_protocols_ServerMessage_descriptor =
      getDescriptor().getMessageTypes().get(9);
    internal_static_p4f_protocols_ServerMessage_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_p4f_protocols_ServerMessage_descriptor,
        new java.lang.String[] { "Opcode", "InfoResponse", "ResponseCode", "OrderRes", "ChangeRes", "ServerResponses", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
