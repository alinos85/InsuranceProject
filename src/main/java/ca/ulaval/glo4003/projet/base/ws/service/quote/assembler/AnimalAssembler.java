package ca.ulaval.glo4003.projet.base.ws.service.quote.assembler;

import ca.ulaval.glo4003.projet.base.ws.application.api.quote.dto.AnimalRequestDTO;
import ca.ulaval.glo4003.projet.base.ws.domain.animal.Animal;

public class AnimalAssembler {

  public AnimalRequestDTO create(Animal animal) {
    AnimalRequestDTO animalRequestDTO = new AnimalRequestDTO();
    animalRequestDTO.animal = animal.getAnimal();
    return animalRequestDTO;
  }

  public Animal create(AnimalRequestDTO animalRequestDTO) {
    Animal animal = new Animal();
    animal.setAnimal(animalRequestDTO.animal);
    return animal;
  }
}
