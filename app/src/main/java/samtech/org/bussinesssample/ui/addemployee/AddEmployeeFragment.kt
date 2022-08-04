package samtech.org.bussinesssample.ui.addemployee

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import kotlinx.coroutines.runBlocking
import samtech.org.bussinesssample.R
import samtech.org.bussinesssample.Singleton
import samtech.org.bussinesssample.database.entities.Employees
import samtech.org.bussinesssample.ui.groceries.GroceriesViewModel

@Suppress("DEPRECATION")
class AddEmployeeFragment : Fragment(), View.OnClickListener {

    private val groceriesViewModel: EmployeesViewModel by activityViewModels {
        EmployeesViewModel.OperatingEmployeesViewModelFactory(
            Singleton.instance!!.employeesRepository
        )
    }

    val REQUEST_IMAGE_CAPTURE = 1
    private lateinit var imageView: ImageView
    private val pickImage = 100
    private var imageUri: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_employee, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnLoadPhoto = view.findViewById<ImageButton>(R.id.fragment_add_grocery_loadphoto)
        val btnTakePhoto = view.findViewById<ImageButton>(R.id.fragment_add_grocery_takephoto)
        val btnSave = view.findViewById<Button>(R.id.fragment_add_grocery_save)
        imageView = view.findViewById(R.id.fragment_add_grocery_imagetaken)

        btnTakePhoto.setOnClickListener(this)
        btnLoadPhoto.setOnClickListener(this)
        btnSave.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.fragment_add_grocery_loadphoto) {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, pickImage)
        }

        if (v?.id == R.id.fragment_add_grocery_takephoto) {
            Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
                takePictureIntent.resolveActivity(v.context.packageManager)?.also {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                }
            }
        }

        if (v?.id == R.id.fragment_add_grocery_save) {
            runBlocking {
                run {
                    /*val emp = Employees(
                        "1234asd34",
                        "Jacinto Garcia Covarrubias",
                        "5565837344",
                        "contact@gmail.com",
                        "Calle 60, Buena Vista, Lerma, Estado de México"
                    )
                    groceriesViewModel.insertEmployee(emp)*/
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data!!.extras!!.get("data") as Bitmap
            imageView.setImageBitmap(imageBitmap)
        }

        if (resultCode == RESULT_OK && requestCode == pickImage) {
            imageUri = data?.data
            imageView.setImageURI(imageUri)
        }
    }
}