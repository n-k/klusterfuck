/*
 * API
 * API
 *
 * OpenAPI spec version: v1
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package com.github.nk.klusterfuck.client.model;

import java.util.Objects;
import com.github.nk.klusterfuck.client.model.IntOrString;
import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * ServicePort
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2017-07-12T18:51:26.870+05:30")
public class ServicePort {
  @SerializedName("name")
  private String name = null;

  @SerializedName("nodePort")
  private Integer nodePort = null;

  @SerializedName("port")
  private Integer port = null;

  @SerializedName("protocol")
  private String protocol = null;

  @SerializedName("targetPort")
  private IntOrString targetPort = null;

  public ServicePort name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ServicePort nodePort(Integer nodePort) {
    this.nodePort = nodePort;
    return this;
  }

   /**
   * Get nodePort
   * @return nodePort
  **/
  @ApiModelProperty(example = "null", value = "")
  public Integer getNodePort() {
    return nodePort;
  }

  public void setNodePort(Integer nodePort) {
    this.nodePort = nodePort;
  }

  public ServicePort port(Integer port) {
    this.port = port;
    return this;
  }

   /**
   * Get port
   * @return port
  **/
  @ApiModelProperty(example = "null", value = "")
  public Integer getPort() {
    return port;
  }

  public void setPort(Integer port) {
    this.port = port;
  }

  public ServicePort protocol(String protocol) {
    this.protocol = protocol;
    return this;
  }

   /**
   * Get protocol
   * @return protocol
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getProtocol() {
    return protocol;
  }

  public void setProtocol(String protocol) {
    this.protocol = protocol;
  }

  public ServicePort targetPort(IntOrString targetPort) {
    this.targetPort = targetPort;
    return this;
  }

   /**
   * Get targetPort
   * @return targetPort
  **/
  @ApiModelProperty(example = "null", value = "")
  public IntOrString getTargetPort() {
    return targetPort;
  }

  public void setTargetPort(IntOrString targetPort) {
    this.targetPort = targetPort;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ServicePort servicePort = (ServicePort) o;
    return Objects.equals(this.name, servicePort.name) &&
        Objects.equals(this.nodePort, servicePort.nodePort) &&
        Objects.equals(this.port, servicePort.port) &&
        Objects.equals(this.protocol, servicePort.protocol) &&
        Objects.equals(this.targetPort, servicePort.targetPort);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, nodePort, port, protocol, targetPort);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ServicePort {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    nodePort: ").append(toIndentedString(nodePort)).append("\n");
    sb.append("    port: ").append(toIndentedString(port)).append("\n");
    sb.append("    protocol: ").append(toIndentedString(protocol)).append("\n");
    sb.append("    targetPort: ").append(toIndentedString(targetPort)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
  
}

