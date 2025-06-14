package library.core.requests;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddReaderRequest {

    private String firstName;
   private String lastName;
}
