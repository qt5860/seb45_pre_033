import React,{useState} from "react";
import styled from "styled-components"; 
import Writer from "./Writer";
import Reply from "./Reply";
import ReplySubmit from "./ReplySubmit";

const AnswerContainer = styled.div`
    width: 1096px;
    display: flex;
    flex-direction: column;
`
const ContentContainer = styled.p`
    width: 1050px;
    padding-left: 10px;
`
const WriterContainer = styled.div`
  height: 90px;
  width: 1096px;
  display: flex;
  justify-content: end;
  align-items: center;
  gap: 30px;
  border-bottom: 1px solid lightgray;
  padding-bottom: 5px;
`;
const WriterDeco = styled.span`
  height: 80px;
  font-size: 15px;
  color: #484848;
  font-weight: bold;
  padding-top: 60px;
`;
const ReplyContainer=styled.div`
    width: 1096px;
    display: flex;
    flex-direction: column;
    align-items: end;
`
const AddReplyButton = styled.button`
    color: gray;
    border: none;
    font-size: 15px;
    margin-right: 35px;
    background-color: transparent;
    margin-bottom: 5px;
    &:hover{
        text-decoration: underline;
    }
`

export default function Answer({ content }) {

    const [submitOn, setSubmitOn] = useState(false)
    const handleSubmitOn = () => {
        setSubmitOn(!submitOn)
    }

    return (
        <AnswerContainer>
            <ContentContainer>{content}</ContentContainer>
            <WriterContainer>
                <WriterDeco>8월 20일 작성됨</WriterDeco>
                <Writer answer={1}/>
            </WriterContainer>
            <ReplyContainer>
                <Reply content={content} />
                <AddReplyButton onClick={handleSubmitOn}>댓글 남기기</AddReplyButton>
                {submitOn?<ReplySubmit/>:<></>}
            </ReplyContainer>
        </AnswerContainer>
    )
}