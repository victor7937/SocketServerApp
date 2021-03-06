package by.victor.jwd.entity;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Sentence implements TextFragment{

    private String sentenceTextForm;
    private List<TextFragment> words;

    public Sentence(String sentenceTextForm) {
        this.sentenceTextForm = sentenceTextForm;
        this.words = new LinkedList<>();
    }

    public void setSentenceTextForm(String sentenceTextForm) { this.sentenceTextForm = sentenceTextForm; }


    @Override
    public String getTextForm() { return sentenceTextForm; }

    @Override
    public List<String> toStringList() {
        return words.stream().map(TextFragment::getTextForm).collect(Collectors.toList());
    }

    @Override
    public List<TextFragment> getFragmentsForm() { return words; }

    @Override
    public int fragmentsCount() { return words.size(); }

    public void addWord (TextFragment textFragment){ words.add(textFragment); }

    public void removeWord (TextFragment textFragment){ words.remove(textFragment); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sentence sentence = (Sentence) o;
        return Objects.equals(sentenceTextForm, sentence.sentenceTextForm) &&
                Objects.equals(words, sentence.words);
    }

    @Override
    public int hashCode() { return Objects.hash(sentenceTextForm, words); }
}
