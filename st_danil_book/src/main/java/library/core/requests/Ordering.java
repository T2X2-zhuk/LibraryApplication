package library.core.requests;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ordering {

	private String orderBy;
	private String orderDirection;

}
