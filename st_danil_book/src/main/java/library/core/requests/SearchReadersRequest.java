package library.core.requests;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchReadersRequest {

    private String firstName;
    private String lastName;

    private Ordering ordering;
    private Paging paging;


    public SearchReadersRequest(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public SearchReadersRequest(String firstName, String lastName, Ordering ordering) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ordering = ordering;
    }
    public SearchReadersRequest(String firstName, String lastName,Paging paging) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.paging = paging;
    }

    public boolean isFirstNameProvided() {
        return this.firstName != null && !this.firstName.isEmpty();
    }

    public boolean isLastNameProvided() {
        return this.lastName != null && !this.lastName.isEmpty();
    }

}
