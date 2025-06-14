package library.core.requests;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeleteReaderBookRequest {

    private String FirstNameReader;
    private String LastNameReader;

    private String title;
    private String author;




}
