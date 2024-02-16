package com.example.e_ticketscanner;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import java.util.List;
@Root(name = "ArrayOfLoginModel", strict = false)
@Namespace(reference = "http://schemas.datacontract.org/2004/07/TICKETING_SYSTEM.Models")
public class ArrayOfLoginModel {
    @ElementList(inline = true, required = false)
    private List<LoginModel> loginModels;

    public List<LoginModel> getLoginModels() {
        return loginModels;
    }

    public void setLoginModels(List<LoginModel> loginModels) {
        this.loginModels = loginModels;
    }
}