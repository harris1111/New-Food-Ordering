// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: PATH/MessageWrapper.proto

package com.p2p.p4f.protocols;

public interface changePassInfoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:p4f_protocols.changePassInfo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string username = 1;</code>
   * @return The username.
   */
  java.lang.String getUsername();
  /**
   * <code>string username = 1;</code>
   * @return The bytes for username.
   */
  com.google.protobuf.ByteString
      getUsernameBytes();

  /**
   * <code>string oldPass = 2;</code>
   * @return The oldPass.
   */
  java.lang.String getOldPass();
  /**
   * <code>string oldPass = 2;</code>
   * @return The bytes for oldPass.
   */
  com.google.protobuf.ByteString
      getOldPassBytes();

  /**
   * <code>string newPass = 3;</code>
   * @return The newPass.
   */
  java.lang.String getNewPass();
  /**
   * <code>string newPass = 3;</code>
   * @return The bytes for newPass.
   */
  com.google.protobuf.ByteString
      getNewPassBytes();
}
