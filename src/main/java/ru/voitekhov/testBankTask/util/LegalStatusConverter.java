package ru.voitekhov.testBankTask.util;

import ru.voitekhov.testBankTask.model.LegalStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class LegalStatusConverter implements AttributeConverter<LegalStatus, String> {
    @Override
    public String convertToDatabaseColumn(LegalStatus legalStatus) {
        return legalStatus == null ? null : legalStatus.getTitle();
    }

    @Override
    public LegalStatus convertToEntityAttribute(String s) {
        return Stream.of(LegalStatus.values())
                .filter(legalStatus -> legalStatus.getTitle().equals(s))
                .findFirst()
                .orElse(null);
    }
}
