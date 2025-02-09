package ru.otus.serialization.data;

import ru.otus.serialization.model.ChatSessionDto;
import ru.otus.serialization.model.MemberDto;
import ru.otus.serialization.model.MessageDto;
import ru.otus.serialization.model.SmsSchemaDto;

import java.util.ArrayList;
import java.util.List;

public class SmsData {
    public SmsSchemaDto chatSessionDto() {

        MemberDto memberDto = MemberDto
                .builder()
                .last("Saint-Petersburg")
                .build();

        MessageDto firstMsg = MessageDto
                .builder()
                .belongNumber("+79219213267")
                .sendDate("03-31-2023 14:09:19")
                .text("PROBLEM CRITICAL - There is hight price of the order")
                .build();

        MessageDto secondMsg = MessageDto
                .builder()
                .belongNumber("+79219213267")
                .sendDate("03-31-2023 14:53:34")
                .text("PROBLEM WARNING - No more free space on the disc")
                .build();

        MessageDto thirdMsg = MessageDto
                .builder()
                .belongNumber("+79219213267")
                .sendDate("03-31-2023 15:09:15")
                .text("PROBLEM CRITICAL - The weather is not good for today")
                .build();

        MessageDto fourthMsg = MessageDto
                .builder()
                .belongNumber("+79219213267")
                .sendDate("03-31-2023 15:53:28")
                .text("PROBLEM WARNING - It's raining like dogs and cats")
                .build();


        List<MemberDto> memberDtoList = new ArrayList<>();
        memberDtoList.add(memberDto);

        List<MessageDto> messageDtoList = new ArrayList<>();
        messageDtoList.add(firstMsg);
        messageDtoList.add(secondMsg);
        messageDtoList.add(thirdMsg);
        messageDtoList.add(fourthMsg);


        ChatSessionDto chatSessionDto = ChatSessionDto
                .builder()
                .chatIdentifier("Apple")
                .memberDtos(memberDtoList)
                .messageDtos(messageDtoList)
                .build();

        List<ChatSessionDto> chatSessionDtoList = new ArrayList<>();
        chatSessionDtoList.add(chatSessionDto);

        return SmsSchemaDto
                .builder()
                .chatSessionDtos(chatSessionDtoList)
                .build();


    }
}
