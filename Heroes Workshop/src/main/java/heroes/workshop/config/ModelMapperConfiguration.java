package heroes.workshop.config;

import heroes.workshop.data.models.Gender;
import heroes.workshop.service.models.heroes.HeroCreateServiceModel;
import heroes.workshop.web.view.models.HeroCreateModel;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfiguration {
    private static ModelMapper mapper;

    static {
        mapper = new ModelMapper();
        initMapper(mapper);
    }

    private static void initMapper(ModelMapper mapper) {
        Converter<String, Gender> stringToGenderConverter =
                ctx -> Gender.valueOf(ctx.getSource().toUpperCase());

        mapper.createTypeMap(HeroCreateModel.class, HeroCreateServiceModel.class)
                .addMappings(map -> map
                        .using(stringToGenderConverter)
                        .map(
                                HeroCreateModel::getGender,
                                HeroCreateServiceModel::setGender
                        )
                );
    }

    @Bean
    public ModelMapper modelMapper() {
        return mapper;
    }
}