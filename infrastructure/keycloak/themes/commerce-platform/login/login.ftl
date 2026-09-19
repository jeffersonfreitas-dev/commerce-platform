<#import "template.ftl" as layout>

<@layout.registrationLayout
    displayMessage=!messagesPerField.existsError('username','password')
    displayInfo=false
; section>

<#if section = "header">

    <div class="ecom-header">
        <h1 class="ecom-title">Bem-vindo</h1>
        <p class="ecom-subtitle">
            Entre na sua conta para continuar
        </p>
    </div>

<#elseif section = "form">

    <form
        id="kc-form-login"
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
                tabindex="1"
                id="username"
                name="username"
                value="${(login.username!'')}"
                type="text"
                autofocus
                autocomplete="username"
                placeholder="seu@email.com"
                aria-invalid="<#if messagesPerField.existsError('username','password')>true</#if>"
            />

            <#if messagesPerField.existsError('username','password')>
                <span class="ecom-error" role="alert">
                    ${kcSanitize(messagesPerField.getFirstError('username','password'))?no_esc}
                </span>
            </#if>
        </div>

        <div class="ecom-field">
            <div class="ecom-label-row">
                <label for="password">${msg("password")}</label>

                <#if realm.resetPasswordAllowed>
                    <a href="${url.loginResetCredentialsUrl}" class="ecom-link">
                        Esqueci minha senha
                    </a>
                </#if>
            </div>

            <input
                tabindex="2"
                id="password"
                name="password"
                type="password"
                autocomplete="current-password"
                placeholder="Digite sua senha"
                aria-invalid="<#if messagesPerField.existsError('username','password')>true</#if>"
            />
        </div>

        <#if realm.rememberMe && !usernameEditDisabled??>
            <div class="ecom-checkbox">
                <input
                    tabindex="3"
                    id="rememberMe"
                    name="rememberMe"
                    type="checkbox"
                    <#if login.rememberMe??>checked</#if>
                />
                <label for="rememberMe">Lembrar de mim</label>
            </div>
        </#if>

        <div class="ecom-actions">
            <input
                tabindex="4"
                type="submit"
                value="${msg('doLogIn')}"
                class="ecom-button"
            />
        </div>

    </form>

    <#if realm.registrationAllowed && !registrationDisabled??>
        <div class="ecom-register">
            <span>Ainda não possui uma conta?</span>
            <a href="${url.registrationUrl}" class="ecom-link">
                Criar conta
            </a>
        </div>
    </#if>

</#if>

</@layout.registrationLayout>