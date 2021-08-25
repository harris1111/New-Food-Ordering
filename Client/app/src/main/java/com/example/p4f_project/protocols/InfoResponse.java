// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: PATH/MessageWrapper.proto

package com.example.p4f_project.protocols;

/**
 * Protobuf type {@code p4f_protocols.InfoResponse}
 */
public final class InfoResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:p4f_protocols.InfoResponse)
    InfoResponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use InfoResponse.newBuilder() to construct.
  private InfoResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private InfoResponse() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new InfoResponse();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private InfoResponse(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 8: {

            reCode_ = input.readInt32();
            break;
          }
          case 18: {
            com.example.p4f_project.protocols.UserAccount.Builder subBuilder = null;
            if (userInfo_ != null) {
              subBuilder = userInfo_.toBuilder();
            }
            userInfo_ = input.readMessage(com.example.p4f_project.protocols.UserAccount.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(userInfo_);
              userInfo_ = subBuilder.buildPartial();
            }

            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.example.p4f_project.protocols.MessageWrapper.internal_static_p4f_protocols_InfoResponse_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.example.p4f_project.protocols.MessageWrapper.internal_static_p4f_protocols_InfoResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.example.p4f_project.protocols.InfoResponse.class, com.example.p4f_project.protocols.InfoResponse.Builder.class);
  }

  public static final int RECODE_FIELD_NUMBER = 1;
  private int reCode_;
  /**
   * <pre>
   *re:
   *- Login: 0 = Success as customer, 1 = User not exists, 2 = Wrong password, 3 = Success as staff
   * </pre>
   *
   * <code>int32 reCode = 1;</code>
   * @return The reCode.
   */
  @java.lang.Override
  public int getReCode() {
    return reCode_;
  }

  public static final int USERINFO_FIELD_NUMBER = 2;
  private com.example.p4f_project.protocols.UserAccount userInfo_;
  /**
   * <code>.p4f_protocols.UserAccount userInfo = 2;</code>
   * @return Whether the userInfo field is set.
   */
  @java.lang.Override
  public boolean hasUserInfo() {
    return userInfo_ != null;
  }
  /**
   * <code>.p4f_protocols.UserAccount userInfo = 2;</code>
   * @return The userInfo.
   */
  @java.lang.Override
  public com.example.p4f_project.protocols.UserAccount getUserInfo() {
    return userInfo_ == null ? com.example.p4f_project.protocols.UserAccount.getDefaultInstance() : userInfo_;
  }
  /**
   * <code>.p4f_protocols.UserAccount userInfo = 2;</code>
   */
  @java.lang.Override
  public com.example.p4f_project.protocols.UserAccountOrBuilder getUserInfoOrBuilder() {
    return getUserInfo();
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (reCode_ != 0) {
      output.writeInt32(1, reCode_);
    }
    if (userInfo_ != null) {
      output.writeMessage(2, getUserInfo());
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (reCode_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, reCode_);
    }
    if (userInfo_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, getUserInfo());
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.example.p4f_project.protocols.InfoResponse)) {
      return super.equals(obj);
    }
    com.example.p4f_project.protocols.InfoResponse other = (com.example.p4f_project.protocols.InfoResponse) obj;

    if (getReCode()
        != other.getReCode()) return false;
    if (hasUserInfo() != other.hasUserInfo()) return false;
    if (hasUserInfo()) {
      if (!getUserInfo()
          .equals(other.getUserInfo())) return false;
    }
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + RECODE_FIELD_NUMBER;
    hash = (53 * hash) + getReCode();
    if (hasUserInfo()) {
      hash = (37 * hash) + USERINFO_FIELD_NUMBER;
      hash = (53 * hash) + getUserInfo().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.example.p4f_project.protocols.InfoResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.example.p4f_project.protocols.InfoResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.example.p4f_project.protocols.InfoResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.example.p4f_project.protocols.InfoResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.example.p4f_project.protocols.InfoResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.example.p4f_project.protocols.InfoResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.example.p4f_project.protocols.InfoResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.example.p4f_project.protocols.InfoResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.example.p4f_project.protocols.InfoResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.example.p4f_project.protocols.InfoResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.example.p4f_project.protocols.InfoResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.example.p4f_project.protocols.InfoResponse parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.example.p4f_project.protocols.InfoResponse prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code p4f_protocols.InfoResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:p4f_protocols.InfoResponse)
      com.example.p4f_project.protocols.InfoResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.example.p4f_project.protocols.MessageWrapper.internal_static_p4f_protocols_InfoResponse_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.example.p4f_project.protocols.MessageWrapper.internal_static_p4f_protocols_InfoResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.example.p4f_project.protocols.InfoResponse.class, com.example.p4f_project.protocols.InfoResponse.Builder.class);
    }

    // Construct using com.example.p4f_project.protocols.InfoResponse.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      reCode_ = 0;

      if (userInfoBuilder_ == null) {
        userInfo_ = null;
      } else {
        userInfo_ = null;
        userInfoBuilder_ = null;
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.example.p4f_project.protocols.MessageWrapper.internal_static_p4f_protocols_InfoResponse_descriptor;
    }

    @java.lang.Override
    public com.example.p4f_project.protocols.InfoResponse getDefaultInstanceForType() {
      return com.example.p4f_project.protocols.InfoResponse.getDefaultInstance();
    }

    @java.lang.Override
    public com.example.p4f_project.protocols.InfoResponse build() {
      com.example.p4f_project.protocols.InfoResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.example.p4f_project.protocols.InfoResponse buildPartial() {
      com.example.p4f_project.protocols.InfoResponse result = new com.example.p4f_project.protocols.InfoResponse(this);
      result.reCode_ = reCode_;
      if (userInfoBuilder_ == null) {
        result.userInfo_ = userInfo_;
      } else {
        result.userInfo_ = userInfoBuilder_.build();
      }
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.example.p4f_project.protocols.InfoResponse) {
        return mergeFrom((com.example.p4f_project.protocols.InfoResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.example.p4f_project.protocols.InfoResponse other) {
      if (other == com.example.p4f_project.protocols.InfoResponse.getDefaultInstance()) return this;
      if (other.getReCode() != 0) {
        setReCode(other.getReCode());
      }
      if (other.hasUserInfo()) {
        mergeUserInfo(other.getUserInfo());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.example.p4f_project.protocols.InfoResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.example.p4f_project.protocols.InfoResponse) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private int reCode_ ;
    /**
     * <pre>
     *re:
     *- Login: 0 = Success as customer, 1 = User not exists, 2 = Wrong password, 3 = Success as staff
     * </pre>
     *
     * <code>int32 reCode = 1;</code>
     * @return The reCode.
     */
    @java.lang.Override
    public int getReCode() {
      return reCode_;
    }
    /**
     * <pre>
     *re:
     *- Login: 0 = Success as customer, 1 = User not exists, 2 = Wrong password, 3 = Success as staff
     * </pre>
     *
     * <code>int32 reCode = 1;</code>
     * @param value The reCode to set.
     * @return This builder for chaining.
     */
    public Builder setReCode(int value) {
      
      reCode_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *re:
     *- Login: 0 = Success as customer, 1 = User not exists, 2 = Wrong password, 3 = Success as staff
     * </pre>
     *
     * <code>int32 reCode = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearReCode() {
      
      reCode_ = 0;
      onChanged();
      return this;
    }

    private com.example.p4f_project.protocols.UserAccount userInfo_;
    private com.google.protobuf.SingleFieldBuilderV3<
        com.example.p4f_project.protocols.UserAccount, com.example.p4f_project.protocols.UserAccount.Builder, com.example.p4f_project.protocols.UserAccountOrBuilder> userInfoBuilder_;
    /**
     * <code>.p4f_protocols.UserAccount userInfo = 2;</code>
     * @return Whether the userInfo field is set.
     */
    public boolean hasUserInfo() {
      return userInfoBuilder_ != null || userInfo_ != null;
    }
    /**
     * <code>.p4f_protocols.UserAccount userInfo = 2;</code>
     * @return The userInfo.
     */
    public com.example.p4f_project.protocols.UserAccount getUserInfo() {
      if (userInfoBuilder_ == null) {
        return userInfo_ == null ? com.example.p4f_project.protocols.UserAccount.getDefaultInstance() : userInfo_;
      } else {
        return userInfoBuilder_.getMessage();
      }
    }
    /**
     * <code>.p4f_protocols.UserAccount userInfo = 2;</code>
     */
    public Builder setUserInfo(com.example.p4f_project.protocols.UserAccount value) {
      if (userInfoBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        userInfo_ = value;
        onChanged();
      } else {
        userInfoBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.p4f_protocols.UserAccount userInfo = 2;</code>
     */
    public Builder setUserInfo(
        com.example.p4f_project.protocols.UserAccount.Builder builderForValue) {
      if (userInfoBuilder_ == null) {
        userInfo_ = builderForValue.build();
        onChanged();
      } else {
        userInfoBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.p4f_protocols.UserAccount userInfo = 2;</code>
     */
    public Builder mergeUserInfo(com.example.p4f_project.protocols.UserAccount value) {
      if (userInfoBuilder_ == null) {
        if (userInfo_ != null) {
          userInfo_ =
            com.example.p4f_project.protocols.UserAccount.newBuilder(userInfo_).mergeFrom(value).buildPartial();
        } else {
          userInfo_ = value;
        }
        onChanged();
      } else {
        userInfoBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.p4f_protocols.UserAccount userInfo = 2;</code>
     */
    public Builder clearUserInfo() {
      if (userInfoBuilder_ == null) {
        userInfo_ = null;
        onChanged();
      } else {
        userInfo_ = null;
        userInfoBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.p4f_protocols.UserAccount userInfo = 2;</code>
     */
    public com.example.p4f_project.protocols.UserAccount.Builder getUserInfoBuilder() {
      
      onChanged();
      return getUserInfoFieldBuilder().getBuilder();
    }
    /**
     * <code>.p4f_protocols.UserAccount userInfo = 2;</code>
     */
    public com.example.p4f_project.protocols.UserAccountOrBuilder getUserInfoOrBuilder() {
      if (userInfoBuilder_ != null) {
        return userInfoBuilder_.getMessageOrBuilder();
      } else {
        return userInfo_ == null ?
            com.example.p4f_project.protocols.UserAccount.getDefaultInstance() : userInfo_;
      }
    }
    /**
     * <code>.p4f_protocols.UserAccount userInfo = 2;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        com.example.p4f_project.protocols.UserAccount, com.example.p4f_project.protocols.UserAccount.Builder, com.example.p4f_project.protocols.UserAccountOrBuilder> 
        getUserInfoFieldBuilder() {
      if (userInfoBuilder_ == null) {
        userInfoBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            com.example.p4f_project.protocols.UserAccount, com.example.p4f_project.protocols.UserAccount.Builder, com.example.p4f_project.protocols.UserAccountOrBuilder>(
                getUserInfo(),
                getParentForChildren(),
                isClean());
        userInfo_ = null;
      }
      return userInfoBuilder_;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:p4f_protocols.InfoResponse)
  }

  // @@protoc_insertion_point(class_scope:p4f_protocols.InfoResponse)
  private static final com.example.p4f_project.protocols.InfoResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.example.p4f_project.protocols.InfoResponse();
  }

  public static com.example.p4f_project.protocols.InfoResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<InfoResponse>
      PARSER = new com.google.protobuf.AbstractParser<InfoResponse>() {
    @java.lang.Override
    public InfoResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new InfoResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<InfoResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<InfoResponse> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.example.p4f_project.protocols.InfoResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

