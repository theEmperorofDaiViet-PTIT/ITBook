package jersey.validator;

import jersey.dao.ProductDAO;
import jersey.entity.Product;
import jersey.form.ProductForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ProductFormValidator implements Validator {

	@Autowired
	private ProductDAO productDAO;

	// Validator này chỉ dùng để kiểm tra class ProductForm.
	@Override
	public boolean supports(Class<?> clazz) {
		return clazz == ProductForm.class;
	}

	@Override
	public void validate(Object target, Errors errors) {
		ProductForm productForm = (ProductForm) target;

		// Kiểm tra các trường (field) của ProductForm.
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "NotEmpty.productForm.id");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "NotEmpty.productForm.title");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "NotEmpty.productForm.price");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "categoryId", "NotEmpty.productForm.categoryId");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "NotEmpty.productForm.description");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "language", "NotEmpty.productForm.language");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "NotEmpty.productForm.numPages");

		String id = productForm.getId();
		if (id != null && id.length() > 0) {
			if (id.matches("\\s+")) {
				errors.rejectValue("id", "Pattern.productForm.id");
			} else if (productForm.isNewProduct()) {
				Product product = productDAO.findProduct(id);
				if (product != null) {
					errors.rejectValue("id", "Duplicate.productForm.id");
				}
			}
		}
	}

}
