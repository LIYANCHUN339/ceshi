package net.myspring.wangzm.common.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springside.modules.utils.collection.CollectionUtil;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by wangzm on 2017 2017/11/21.
 */
public class RestResponse {
    private Boolean success;
    private String message;
    private String code;
    @JsonIgnore
    private BindingResult bindingResult;

    private List<RestErrorField> errors = Lists.newArrayList();

    private Map<String,Object> extra = Maps.newHashMap();


    public RestResponse(String message,String code) {
        this.success = true;
        this.message = message;
        this.code = code;
    }

    public RestResponse(BindingResult bindingResult,String message,String code) {
        this.success = false;
        this.message = message;
        this.code = code;
        this.bindingResult=bindingResult;
    }

    public RestResponse(String message,String code,Boolean success) {
        this.message = message;
        this.code = code;
        this.success = success;
    }

    public RestResponse(){}


    public Boolean getSuccess() {
        if((bindingResult!=null&&bindingResult.hasFieldErrors()|| CollectionUtil.isNotEmpty(errors))) {
            success = false;
        }
        return success;
    }

    public void addError(String code, String field, String msg, Object... msgArgs) {
        this.errors.add(new RestErrorField(String.format(msg, msgArgs), code, field));
    }

    public void addErrors(Collection<RestErrorField> errors) {
        this.errors.addAll(errors);
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Map<String, Object> getExtra() {
        if(bindingResult!=null&&bindingResult.hasFieldErrors()){
            Map<String,RestErrorField> errors=Maps.newHashMap();
            for(FieldError error:bindingResult.getFieldErrors()){
                errors.put(error.getField(),new RestErrorField(error,null));
            }
            extra.put("errors",errors);
        }
        return extra;
    }

    public void setExtra(Map<String, Object> extra) {
        this.extra = extra;
    }

    public BindingResult getBindingResult() {
        return bindingResult;
    }

    public void setBindingResult(BindingResult bindingResult) {
        this.bindingResult = bindingResult;
    }

    public List<RestErrorField> getErrors() {
        return errors;
    }

    public void setErrors(List<RestErrorField> errors) {
        this.errors = errors;
    }
}
