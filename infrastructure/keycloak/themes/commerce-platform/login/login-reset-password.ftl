<#import "template.ftl" as layout>

<@layout.registrationLayout
    displayMessage=!messagesPerField.existsError('username')
; section>

<#if section = "header">

    <div class="ecom-header">
        <h1 class="ecom-title">Recuperar senha</h1>
        <p class="ecom-subtitle">
            Informe seu e-mail e enviaremos um link para redefinir sua senha
        </p>
    </div>

<#elseif section = "form">

    <form
        id="kc-reset-password-form"
        action="${url.loginAction}"
        method="post"
        class="ecom-form"
        novalidate
    >

        <div class="ecom-field">
            <label for="username">
                <#if !realm.loginWithEmailAllowed>
                    ${msg("username")}
                <#elseif !realm.registrationEmailAsUsername>
                    ${msg("usernameOrEmail")}
                <#else>
                    ${msg("email")}
                </#if>
            </label>

            <input
                type="text"
                id="username"
                name="username"
                value="${(auth.attemptedUsername!'')}"
                autofocus
                autocomplete="username"
                placeholder="seu@email.com"
                aria-invalid="<#if messagesPerField.existsError('username')>true</#if>"
            />

            <#if messagesPerField.existsError('username')>
                <span class="ecom-error" role="alert">
                    ${kcSanitize(messagesPerField.get('username'))?no_esc}
                </span>
            </#if>
        </div>

        <div class="ecom-actions">
            <input
                type="submit"
                value="${msg('doSubmit')}"
                class="ecom-button"
            />
        </div>

    </form>

    <div class="ecom-register">
        <a href="${url.loginUrl}" class="ecom-link">
            ← Voltar para o login
        </a>
    </div>

</#if>

</@layout.registrationLayout>