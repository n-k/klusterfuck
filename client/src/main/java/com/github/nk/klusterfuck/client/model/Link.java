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
import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Link
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2017-07-12T18:51:26.870+05:30")
public class Link {
  @SerializedName("from")
  private String from = null;

  @SerializedName("to")
  private String to = null;

  @SerializedName("uiProps")
  private Map<String, Object> uiProps = new HashMap<String, Object>();

  public Link from(String from) {
    this.from = from;
    return this;
  }

   /**
   * Get from
   * @return from
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getFrom() {
    return from;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public Link to(String to) {
    this.to = to;
    return this;
  }

   /**
   * Get to
   * @return to
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getTo() {
    return to;
  }

  public void setTo(String to) {
    this.to = to;
  }

  public Link uiProps(Map<String, Object> uiProps) {
    this.uiProps = uiProps;
    return this;
  }

  public Link putUiPropsItem(String key, Object uiPropsItem) {
    this.uiProps.put(key, uiPropsItem);
    return this;
  }

   /**
   * Get uiProps
   * @return uiProps
  **/
  @ApiModelProperty(example = "null", value = "")
  public Map<String, Object> getUiProps() {
    return uiProps;
  }

  public void setUiProps(Map<String, Object> uiProps) {
    this.uiProps = uiProps;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Link link = (Link) o;
    return Objects.equals(this.from, link.from) &&
        Objects.equals(this.to, link.to) &&
        Objects.equals(this.uiProps, link.uiProps);
  }

  @Override
  public int hashCode() {
    return Objects.hash(from, to, uiProps);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Link {\n");
    
    sb.append("    from: ").append(toIndentedString(from)).append("\n");
    sb.append("    to: ").append(toIndentedString(to)).append("\n");
    sb.append("    uiProps: ").append(toIndentedString(uiProps)).append("\n");
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

