package swExpertAcademy.professional

object ProInputFile {
    private const val INPUT_FILE_PATH = "src/swExpertAcademy/professional/_inputs/"

    @JvmStatic
    fun number(index: Int): String {
        return "${INPUT_FILE_PATH}input_pro_p${index}.txt"
    }
}