<#import "template.ftl" as layout>

<@layout.registrationLayout
    displayMessage=!messagesPerField.existsError('totp')
; section>

<#if section = "header">

    <div class="ecom-header">
        <h1 class="ecom-title">Verificação em duas etapas</h1>
        <p class="ecom-subtitle">
            Digite o código de 6 dígitos gerado pelo seu aplicativo autenticador
        </p>
    </div>

<#elseif section = "form">

    <form
        id="kc-otp-login-form"
        action="${url.loginAction}"
        method="post"
        class="ecom-form"
        novalidate
    >

        <div class="ecom-field">
            <label for="otp">${msg("loginOtpOneTime")}</label>
            <input
                type="text"
                id="otp"
                name="otp"
                autofocus
                autocomplete="one-time-code"
                inputmode="numeric"
                pattern="[0-9]*"
                maxlength="6"
                placeholder="000000"
                class="ecom-input-otp"
                aria-invalid="<#if messagesPerField.existsError('totp')>true</#if>"
            />

            <#if messagesPerField.existsError('totp')>
                <span class="ecom-error" role="alert">
                    ${kcSanitize(messagesPerField.get('totp'))?no_esc}
                </span>
            </#if>
        </div>

        <div class="ecom-actions">
            <input
                type="submit"
                value="${msg('doLogIn')}"
                class="ecom-button"
            />
        </div>

    </form>

    <#if otpLogin.userReAuthenticate?? && otpLogin.userReAuthenticate>
        <div class="ecom-info">
            <a href="${url.loginRestartFlowUrl!'#'}" class="ecom-link">
                ← Recomeçar
            </a>
        </div>
    </#if>

</#if>

</@layout.registrationLayout>