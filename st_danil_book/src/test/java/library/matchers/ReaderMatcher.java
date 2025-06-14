package library.matchers;


import library.core.domain.Reader;
import org.mockito.ArgumentMatcher;
import org.springframework.stereotype.Component;


public class ReaderMatcher implements ArgumentMatcher<Reader> {
    private String firstName;
    private String lastName;

    public ReaderMatcher(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }


    @Override
    public boolean matches(Reader argument) {
        return argument.getFirst_name().equals(firstName)
                && argument.getLast_name().equals(lastName);
    }


}
