package library.core.requests;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Paging {

	private Integer pageNumber;
	private Integer pageSize;


}
