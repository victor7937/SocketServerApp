package by.victor.jwd.entity;

import java.util.List;
import java.util.Objects;

public class Word implements TextFragment{

    private String wordTextForm;
    private List<String> wordSymbols;

    public Word(String wordTextForm, List<String> wordSymbols) {
        this.wordTextForm = wordTextForm;
        this.wordSymbols = wordSymbols;
    }

    public void setWordTextForm(String wordTextForm) { this.wordTextForm = wordTextForm; }

    public void setWordSymbols(List<String> wordSymbols) { this.wordSymbols = wordSymbols; }

    @Override
    public String getTextForm() { return wordTextForm; }

    @Override
    public List<String> toStringList() { return wordSymbols; }

    @Override
    public List<TextFragment> getFragmentsForm() { return null; }

    @Override
    public int fragmentsCount() { return wordSymbols.size(); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word = (Word) o;
        return Objects.equals(wordTextForm, word.wordTextForm);
    }

    @Override
    public int hashCode() { return Objects.hash(wordTextForm, wordSymbols); }
}
