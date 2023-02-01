package ca.ulaval.glo4003.projet.base.ws.service.user.assembler;

import ca.ulaval.glo4003.projet.base.ws.application.api.user.dto.SignupRequestDTO;
import ca.ulaval.glo4003.projet.base.ws.domain.actuary.Actuary;
import ca.ulaval.glo4003.projet.base.ws.domain.common.email.EmailAddress;
import ca.ulaval.glo4003.projet.base.ws.domain.student.Student;

public class UserAssembler {

  public Student createStudent(SignupRequestDTO signupRequestDto) {
    String hashedPassword = org.apache.commons.codec.digest.DigestUtils.sha256Hex(signupRequestDto.password);
    EmailAddress emailValueObject = new EmailAddress(signupRequestDto.email);
    return new Student(emailValueObject, hashedPassword);
  }

  public Actuary createActuary(SignupRequestDTO signupRequestDto) {
    String hashedPassword = org.apache.commons.codec.digest.DigestUtils.sha256Hex(signupRequestDto.password);
    EmailAddress emailValueObject = new EmailAddress(signupRequestDto.email);
    return new Actuary(emailValueObject, hashedPassword);
  }
}
