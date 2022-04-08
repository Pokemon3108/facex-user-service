package by.daryazaleuskaya

interface DtoConverterService<M,D> {

    fun dataModelToDto(model: M): D

    fun dtoToDataModel(dto : D) : M
}