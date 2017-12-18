package Elements;

import org.openqa.selenium.WebElement;

public class CheckBox extends Element{
    public CheckBox(WebElement element){
        super(element);
    }

    public void Checked(){
        if(!isSelected()) click();
    }
}
