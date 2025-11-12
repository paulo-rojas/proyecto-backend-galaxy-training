package com.galaxy.training.backend.hateoas;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNull;
import org.springframework.data.web.PagedResourcesAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Component;

import com.galaxy.training.backend.controllers.EscuelaConductorController;
import com.galaxy.training.backend.dtos.out.EscuelaConductorResponseDto;


@Component
public class EscuelaConductorAssembler implements RepresentationModelAssembler<EscuelaConductorResponseDto, EntityModel<EscuelaConductorResponseDto>> {

	@Override
	@NonNull
	public EntityModel<EscuelaConductorResponseDto> toModel(@NonNull EscuelaConductorResponseDto dto) {
		EntityModel<EscuelaConductorResponseDto> model = EntityModel.of(dto);

		model.add(linkTo(methodOn(EscuelaConductorController.class).getEscuelaConductorById(dto.getId())).withSelfRel());

		model.add(linkTo(methodOn(EscuelaConductorController.class).updateEscuelaConductor(dto.getId(), null)).withRel("update"));

		model.add(linkTo(methodOn(EscuelaConductorController.class).deleteEscuelaConductor(dto.getId())).withRel("delete"));

		return model;
	}

	public PagedModel<EntityModel<EscuelaConductorResponseDto>> toPagedModel(
			org.springframework.data.domain.Page<EscuelaConductorResponseDto> page,
			PagedResourcesAssembler<EscuelaConductorResponseDto> pagedResourcesAssembler) {

		if (page == null || pagedResourcesAssembler == null) {
			return PagedModel.empty();
		}

		return pagedResourcesAssembler.toModel(page, this);
	}
}
