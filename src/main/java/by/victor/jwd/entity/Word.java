package by.victor.jwd.entity;

import java.util.List;

public class Word implements TextFragment{

    private String wordTextForm;
    private List<String> wordSymbols;

    public Word(String wordTextForm, List<String> wordSymbols) {
        this.wordTextForm = wordTextForm;
        this.wordSymbols = wordSymbols;
    }

    public void setWordTextForm(String wordTextForm) {
        this.wordTextForm = wordTextForm;
    }

    public void setWordSymbols(List<String> wordSymbols) {
        this.wordSymbols = wordSymbols;
    }

    @Override
    public String getTextForm() {
        return wordTextForm;
    }

    @Override
    public List<String> toStringList() {
        return wordSymbols;
    }

    @Override
    public List<TextFragment> getFragmentsForm() {
        return null;
    }

    @Override
    public int fragmentsCount() {
        return wordSymbols.size();
    }
}
