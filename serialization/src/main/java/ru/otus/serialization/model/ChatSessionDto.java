
package ru.otus.serialization.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "chat_id",
    "chat_identifier",
    "display_name",
    "is_deleted",
    "members",
    "messages"
})
public class ChatSessionDto {

    @JsonProperty("chat_id")
    private Integer chatId;

    @JsonProperty("chat_identifier")
    private String chatIdentifier;

    @JsonProperty("display_name")
    private String displayName;

    @JsonProperty("is_deleted")
    private Integer isDeleted;

    @JsonProperty("members")
    private List<MemberDto> memberDtos = new ArrayList<>();

    @JsonProperty("messages")
    private List<MessageDto> messageDtos = new ArrayList<>();



}
