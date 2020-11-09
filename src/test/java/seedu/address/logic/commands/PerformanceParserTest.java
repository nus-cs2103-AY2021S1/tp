package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.quiz.Attempt;
import seedu.address.model.quiz.Mcq;
import seedu.address.model.quiz.Question;
import seedu.address.model.quiz.Response;
import seedu.address.model.quiz.TrueFalse;
import seedu.address.model.quiz.UniqueResponseList;
import seedu.address.model.quiz.exceptions.InvalidQuestionAnswerException;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.storage.AttemptParser;
import seedu.address.storage.QuestionParser;
import seedu.address.storage.ResponseParser;

public class PerformanceParserTest {

    private QuestionParser questionParser = new QuestionParser();
    private ResponseParser responseParser = new ResponseParser();
    private AttemptParser attemptParser = new AttemptParser();

    private String stringifyQuestion(Question question) {
        String inputString = "";
        if (question.isMcq()) {
            Mcq mcq = (Mcq) (question);
            String prompt = mcq.getPrompt();
            int answer = mcq.getAnswer();
            List<String> options = mcq.getOptions();
            inputString = prompt + "##" + answer + "##";
            for (int i = 0; i < options.size(); i++) {
                if (i != options.size() - 1) {
                    inputString += options.get(i) + "#";
                } else {
                    inputString += options.get(i);
                }
            }
        } else {
            TrueFalse trueFalse = (TrueFalse) (question);
            String prompt = trueFalse.getPrompt();
            boolean answer = trueFalse.getAnswer();
            inputString = prompt + "##" + answer + "##";
        }
        return inputString;
    }

    private String stringifyResponse(String response, Question question) throws
            InvalidQuestionAnswerException {
        String inputString = response + "###" + question.checkResponse(response)
                + "###" + stringifyQuestion(question);
        return inputString;
    }

    private String stringifyAttempt(String time, String[] responses) {
        String inputString = time + "####";
        for (int i = 0; i < responses.length; i++) {
            if (i != responses.length - 1) {
                inputString += responses[i] + "|";
            } else {
                inputString += responses[i];
            }
        }
        return inputString;
    }

    @Test
    public void executeParseQuestionTrueFalse_success() {
        Question question = SampleDataUtil.getSampleQuestions()[0];
        String inputString = stringifyQuestion(question);
        assertEquals(question, questionParser.parseQuestion(inputString));
    }

    @Test
    public void executeParseQuestionMcq_success() {
        Question question = SampleDataUtil.getSampleQuestions()[5];
        String inputString = stringifyQuestion(question);
        assertEquals(question, questionParser.parseQuestion(inputString));
    }

    @Test
    public void executeParseResponseTrueFalse_success() throws
            InvalidQuestionAnswerException {
        Question question = SampleDataUtil.getSampleQuestions()[1];
        String reply = "true";
        String inputString = stringifyResponse(reply, question);
        Response expectedResponse = new Response(reply, question, question.checkResponse(reply));
        assertEquals(expectedResponse, responseParser.parseResponseFields(inputString));
    }

    @Test
    public void executeParseResponseMcq_success() throws
            InvalidQuestionAnswerException {
        Question question = SampleDataUtil.getSampleQuestions()[6];
        String reply = "1";
        String inputString = stringifyResponse(reply, question);
        Response expectedResponse = new Response(reply, question, question.checkResponse(reply));
        assertEquals(expectedResponse, responseParser.parseResponseFields(inputString));
    }

    @Test
    public void executeParseAttempt_success() throws
            InvalidQuestionAnswerException {
        Question trueFalseQuestion = SampleDataUtil.getSampleQuestions()[2];
        Question mcqQuestion = SampleDataUtil.getSampleQuestions()[7];
        String[] responses = new String[]{stringifyResponse("true",
                trueFalseQuestion), stringifyResponse("1", mcqQuestion)};
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localTime = LocalDateTime.now();
        String time = dtf.format(localTime);
        String inputString = stringifyAttempt(time, responses);

        Response trueFalseResponse = new Response("true", trueFalseQuestion,
                trueFalseQuestion.checkResponse("true"));
        Response mcqResponse = new Response("1", mcqQuestion,
                mcqQuestion.checkResponse("1"));
        UniqueResponseList responseList = new UniqueResponseList();
        responseList.add(trueFalseResponse);
        responseList.add(mcqResponse);
        Attempt expectedAttempt = new Attempt(responseList, LocalDateTime.parse(time, dtf));
        assertEquals(expectedAttempt, attemptParser.parseAttempt(inputString));
    }
}
