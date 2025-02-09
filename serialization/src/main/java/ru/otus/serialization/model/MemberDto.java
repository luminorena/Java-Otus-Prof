
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
    "first",
    "handle_id",
    "image_path",
    "last",
    "middle",
    "phone_number",
    "service",
    "thumb_path"
})
public class MemberDto {

    @JsonProperty("first")
    private String first;

    @JsonProperty("handle_id")
    private Integer handleId;

    @JsonProperty("image_path")
    private String imagePath;

    @JsonProperty("last")
    private String last;

    @JsonProperty("middle")
    private String middle;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("service")
    private String service;

    @JsonProperty("thumb_path")
    private String thumbPath;



}
