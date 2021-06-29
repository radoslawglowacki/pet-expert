package com.radekdawid.petexpert.validation.password;

import org.passay.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    private DictionaryRule dictionaryRule;

    @Override
    public void initialize(ValidPassword constraintAnnotation) {
//        try {
//            String invalidPasswordList = this.getClass().getResource("/invalid-password-list.txt").getFile();
//            dictionaryRule = new DictionaryRule(
//                    new WordListDictionary(WordLists.createFromReader(
//                            new FileReader[] {
//                                    new FileReader(invalidPasswordList)
//                            },
//                            false,
//                            new ArraysSort()
//                    )));
//        } catch (IOException e) {
//            throw new RuntimeException("could not load word list", e);
//        }
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        System.out.println(password);
        PasswordValidator validator = new PasswordValidator(Arrays.asList(

                new LengthRule(6, 20),

                new CharacterRule(PolishCharacterData.UpperCase, 1),

                new CharacterRule(PolishCharacterData.LowerCase, 1),

                new CharacterRule(EnglishCharacterData.Digit, 1),

                new CharacterRule(EnglishCharacterData.Special, 1),

                new WhitespaceRule()

//                dictionaryRule
        ));

        RuleResult result = validator.validate(new PasswordData(password));

        if (result.isValid()) {
            return true;
        }

        List<String> messages = validator.getMessages(result);
        String messageTemplate = messages.stream().collect(Collectors.joining(","));
        context.buildConstraintViolationWithTemplate(messageTemplate)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
        return false;
    }
}
