package mianshi.tool.mapstruct;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author: hike97
 * @createTime: 2023/04/05 10:08
 * @description: TODO
 */
@Mapper
public interface StudentMapper {

    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    /**
     * vo转 domain
     * @param studentVO
     * @return
     */
    Student toStudent(StudentVO studentVO);
}
