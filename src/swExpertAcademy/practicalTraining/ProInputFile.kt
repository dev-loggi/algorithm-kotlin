package swExpertAcademy.practicalTraining

object ProInputFile {
    private const val INPUT_FILE_PATH = "src/swExpertAcademy/practicalTraining/_inputs/"

    @JvmStatic
    fun number(index: Int): String {
        return "${INPUT_FILE_PATH}input_pro_p${index}.txt"
    }
}