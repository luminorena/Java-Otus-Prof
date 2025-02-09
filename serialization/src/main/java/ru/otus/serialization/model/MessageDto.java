
package ru.otus.serialization.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "ROWID",
    "attributedBody",
    "belong_number",
    "date",
    "date_read",
    "guid",
    "handle_id",
    "has_dd_results",
    "is_deleted",
    "is_from_me",
    "send_date",
    "send_status",
    "service",
    "text"
})
public class MessageDto {

    @JsonProperty("ROWID")
    private Integer rowid;

    @JsonProperty("attributedBody")
    private String attributedBody;

    @JsonProperty("belong_number")
    private String belongNumber;

    @JsonProperty("date")
    private Integer date;

    @JsonProperty("date_read")
    private Integer dateRead;

    @JsonProperty("guid")
    private String guid;

    @JsonProperty("handle_id")
    private Integer handleId;

    @JsonProperty("has_dd_results")
    private Integer hasDdResults;

    @JsonProperty("is_deleted")
    private Integer isDeleted;

    @JsonProperty("is_from_me")
    private Integer isFromMe;

    @JsonProperty("send_date")
    private String sendDate;

    @JsonProperty("send_status")
    private Integer sendStatus;

    @JsonProperty("service")
    private String service;

    @JsonProperty("text")
    private String text;

}
