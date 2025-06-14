package library.core.requests;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindAllBooksWhichReadingReaderRequest {

    private String readerFirstName;
    private String readerLastName;

}
