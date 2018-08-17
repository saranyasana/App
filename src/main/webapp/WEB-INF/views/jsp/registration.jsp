<%@ taglib prefix="blog-tags" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<blog-tags:header />
<c:set var="contextPath" value="${pageContext.servletContext.contextPath}" />


    <title>Create an account</title>

<div class="container">
    <div class="row">
        <div class="col-sm-2 col-md-3"></div>
        <div class="col-sm-8 col-md-6">
            <div class="well well-lg">
            
   <form id="userForm" name="f" action="/login" method="POST">
   
        <h2 class="form-signin-heading">Create your account</h2>
        
         <div class="form-group">
                        <label for="nameInput">Name</label>
                        <input id="nameInput" class="form-control" name="name" type="text" />
                    </div>
                    
                    <div class="form-group">
                        <label for="emaildInput">Email</label>
                        <input id="emailInput" class="form-control" name="email" type="text" />
                    </div>
                             
                    <div class="form-group">
                        <label for="usernameInput">Username</label>
                        <input id="usernameInput" class="form-control" name="username" type="text" />
                    </div>
                    
                    <div class="form-group">
                        <label for="passwordInput">Password</label>
                        <input id="passwordInput" class="form-control" name="password" type="password" />
                    </div>
                        
                    <button type="submit" id="submitButton" class="btn btn-primary">Submit</button>
                    <a id="cancelButton" href="${contextPath}/" class="btn btn-danger">Cancel</a>
                      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 
  
            </form>
   </div>
        <div class="col-sm-2 col-md-3"></div>
    </div>
</div>
</div>
<script type="text/javascript">
    
    $(document).ready(function(){
        $("#usernameInput").focus();
    });
    
</script>
    
<blog-tags:footer />