package by.victor.jwd.entity;

import java.util.List;

public interface TextFragment {
    String getTextForm();
    List<String> toStringList();
    List <TextFragment> getFragmentsForm();
    int fragmentsCount();
}
