package login;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString


public class LoginVO {
	private String id, passwd, name, email;
}
