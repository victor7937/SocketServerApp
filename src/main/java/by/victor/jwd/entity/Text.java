package by.victor.jwd.entity;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Text implements TextFragment{

    private String textForm;
    private List<String> codeBlocks;
    private List<TextFragment> sentences;

    public Text(String textForm, List<String> codeBlocks) {
        this.textForm = textForm;
        this.codeBlocks = codeBlocks;
        this.sentences = new LinkedList<>();
    }

    public List<String> getCodeBlocks() {
        return codeBlocks;
    }

    @Override
    public String getTextForm() {
        return textForm;
    }

    @Override
    public List<String> toStringList() {
        return sentences.stream().map(TextFragment::getTextForm).collect(Collectors.toList());
    }

    @Override
    public List<TextFragment> getFragmentsForm() {
        return sentences;
    }

    @Override
    public int fragmentsCount() {
        return sentences.size();
    }

    public void addSentence (TextFragment textFragment){
        sentences.add(textFragment);
    }

    public void removeSentence (TextFragment textFragment){
        sentences.remove(textFragment);
    }
}
