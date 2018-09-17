import java.util.Scanner;
/**
 * Class for question.
 */
class Question {
    /**
     * { var_description }.
     */
    private String questiontext;
    /**
     * { var_description }.
     */
    private String[] choices;
    /**
     * { var_description }.
     */
    private int correctAnswer;
    /**
     * { var_description }.
     */
    private int maxMarks;
    /**
     * { var_description }.
     */
    private int penalty;
    /**
     * { var_description }.
     */
    private String response;
    /**
     * Constructs the object.
     */
    Question() {

    }
    /**
     * Constructs the object.
     *
     * @param      question1       The question 1
     * @param      choices1        The choices 1
     * @param      correctAnswer1  The correct answer 1
     * @param      maxMarks1       The maximum marks 1
     * @param      penalty1        The penalty 1
     */
    Question(final String question1, final String[] choices1,
        final int correctAnswer1, final int maxMarks1, final int penalty1) {
        this.questiontext = question1;
        this.choices = choices1;
        this.correctAnswer = correctAnswer1;
        this.maxMarks = maxMarks1;
        this.penalty = penalty1;
        this.response = null;
    }
    /**
     * { function_description }.
     *
     * @param      choice  The choice
     *
     * @return     { description_of_the_return_value }
     */
    public boolean evaluateResponse() {
        if (choices[correctAnswer - 1].equals(response)){
            return true;
        }
        return false;
    }
    /**
     * Gets the correct answer.
     *
     * @return     The correct answer.
     */
    public String getCorrectAnswer() {
        return null;
    }
    /**
     * Gets the question text.
     *
     * @return     The question text.
     */
    public String getQuestionText() {
        return questiontext;
    }
    /**
     * Gets the choice.
     *
     * @return     The choice.
     */
    public String[] getChoice() {
        return choices;
    }
    /**
     * Gets the maximum marks.
     *
     * @return     The maximum marks.
     */
    public int getMaxMarks() {
        return maxMarks;
    }
    /**
     * Gets the penalty.
     *
     * @return     The penalty.
     */
    public int getPenalty() {
        return penalty;
    }
    /**
     * Sets the response.
     *
     * @param      answer  The answer
     */
    public void setResponse(final String answer) {
        this.response = answer;
    }
    /**
     * Gets the response.
     *
     * @return     The response.
     */
    public String getResponse() {
        return response;
    }
    /**
     * Returns a string representation of the object.
     *
     * @return     String representation of the object.
     */
    public String toString() {
        String s = "";
        s += this.questiontext + "(" + getMaxMarks() + ")\n";
        if (this.choices.length == 1){
            s += this.choices[0];
        } else if(this.choices.length == 2) {
            s += this.choices[0] + "\t" + this.choices[1];
        } else if(this.choices.length == 3) {
            s += this.choices[0] + "\t" + this.choices[1] + "\t" + this.choices[2];
        } else if(this.choices.length == 4) {
            s += this.choices[0] + "\t" + this.choices[1] + "\t" + this.choices[2] + "\t" + this.choices[3];
        }
        s += "\n";
        return s;
    }
}
/**
 * Class for quiz.
 */
class Quiz {
    /**
     * { var_description }.
     */
    private final int onehundred = 100;
    /**
     * { var_description }.
     */
    private Question[] questions;
    /**
     * { var_description }.
     */
    private int size;
    /**
     * Constructs the object.
     */
    Quiz() {
        questions = new Question[onehundred];
        size = 0;
    }
    /**
     * Adds a question.
     *
     * @param      q     The question
     */
    public void addQuestion(final Question q) {
        questions[size++] = q;
    }

    /**
     * Gets the size.
     *
     * @return     The size.
     */
    public int getSize(){
        return size;
    }
    /**
     * Gets the question.
     *
     * @param      index  The index
     *
     * @return     The question.
     */
    public Question getQuestion(final int index) {
        if (index < size) {
            return questions[index];
        }
        return null;
    }
    /**
     * Shows the report.
     *
     * @return     { description_of_the_return_value }
     */
    public String showReport() {
        String s = "";
        if (size > 0){
            int total = 0;
            for (int i = 0; i < size; i++) {
                s += questions[i].getQuestionText() + "\n";
                String[] choices = questions[i].getChoice();
                if (questions[i].evaluateResponse()){
                    s += " Correct Answer! - Marks Awarded: " +  questions[i].getMaxMarks();
                    total += questions[i].getMaxMarks();
                } else {
                    s += " Wrong Answer! - Penalty: " +  questions[i].getPenalty();
                    total += questions[i].getPenalty();
                }
                s += "\n";
            }
            s += "Total Score: " + total;
        }
        return s;
    }
}
/**
 * Solution class for code-eval.
 */
public final class Solution {
     /**
     * Constructs the object.
     */
    private Solution() {
        // leave this blank
    }
    /**
     * main function to execute test cases.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
         // instantiate this Quiz
        Quiz q = new Quiz();
         // code to read the test cases input file
        Scanner s = new Scanner(System.in);
        // check if there is one more line to process
        while (s.hasNext()) {
            // read the line
            String line = s.nextLine();
             // split the line using space
            String[] tokens = line.split(" ");
              // based on the list operation invoke the corresponding method
            switch (tokens[0]) {
                case "LOAD_QUESTIONS":
                System.out.println("|----------------|");
                System.out.println("| Load Questions |");
                System.out.println("|----------------|");
                loadQuestions(s, q, Integer.parseInt(tokens[1]));
                break;
                case "START_QUIZ":
                System.out.println("|------------|");
                System.out.println("| Start Quiz |");
                System.out.println("|------------|");
                startQuiz(s, q, Integer.parseInt(tokens[1]));
                break;
                case "SCORE_REPORT":
                System.out.println("|--------------|");
                System.out.println("| Score Report |");
                System.out.println("|--------------|");
                displayScore(q);
                break;
                default:
                break;
            }
        }
    }
    /**
     * Loads questions.
     *
     * @param      scan       The scan
     * @param      quiz       The quiz
     * @param      q          The question count
     *
     */
    public static void loadQuestions(final Scanner scan,
        final Quiz quiz, final int q) {
        // write your code here to read the questions from the console
        // tokenize the question line and create the question object
        // add the question objects to the quiz class
        if (q > 0){
            int i = 0;
            boolean flag = false;
            boolean flag1 = false;
            boolean choiceout = false;
            boolean choicessesless = false;
            boolean invalidmax = false;
            boolean invalidpenalty = false;
            boolean questionnull = false;
            while(i < q){
                String line = scan.nextLine();
                String[] tokens = line.split(":");
                String[] optionstokens = tokens[1].split(",");
                if (tokens[0].equals("")) {
                	questionnull = true;
                	break;
                }

                if(Integer.parseInt(tokens[2]) > optionstokens.length){
                    if(optionstokens.length <= 4){
                        choicessesless = true;
                        break;
                    }
                    choiceout = true;
                    break;
                }
                if(tokens.length < 5){
                    flag = true;
                    break;
                }
                if (Integer.parseInt(tokens[3]) <= 0) {
                	invalidmax = true;
                	break;
                }
                if (Integer.parseInt(tokens[4]) >= 0) {
                	invalidpenalty = true;
                	break;
                }
                Question newQuiz = new Question(tokens[0], optionstokens, Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4]));
                quiz.addQuestion(newQuiz);
                i++;
            }
            if (flag) {
                System.out.println("Error! Malformed question");
            } else if(choiceout){
                System.out.println("Error! Correct answer choice number is out of range for question text 1");
            } else if(choicessesless){
                System.out.println("trick question  does not have enough answer choices");
            } else if(invalidmax){
                System.out.println("Invalid max marks for question about sony");
            } else if(invalidpenalty){
                System.out.println("Invalid penalty for question about sony");
            } else if(questionnull){
                System.out.println("Error! Malformed question");
            } else if(!flag1){
                System.out.println(q + " are added to the quiz");
            }
        } else {
            System.out.println("Quiz does not have questions");
        }
    }
    /**
     * Starts a quiz.
     *
     * @param      scan  The scan
     * @param      quiz  The quiz
     * @param      q     The answer count
     */
    public static void startQuiz(final Scanner scan,
        final Quiz quiz, final int q) {
        // write your code here to display the quiz questions on the console.
        // read the user responses from the console using scanner object.
        // store the user respone in the question object
        if(quiz.getSize() > 0){
            // quiz.printQuestions();
            for (int i = 0; i < q; i++) {
                Question s = quiz.getQuestion(i);
                System.out.println(s);
                String line = scan.nextLine();
                // String[] tokens = line.split(" ");
                s.setResponse(line);
            }
        }
    }
    /**
     * Displays the score report.
     *
     * @param      quiz     The quiz object
     */
    public static void displayScore(final Quiz quiz) {
        // write your code here to display the score report using quiz object.
        System.out.print(quiz.showReport());
    }
}
