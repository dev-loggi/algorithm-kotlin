package swExpertAcademy.basicLearning._inputs

object InputFile {

    private const val INPUT_FILE_PATH = "src/swExpertAcademy/basicLearning/_inputs/"

    @JvmStatic
    fun number(index: Int): String {
        return "${INPUT_FILE_PATH}input_p${index}.txt"
    }
}