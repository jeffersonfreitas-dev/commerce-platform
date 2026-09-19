<#import "template.ftl" as layout>

<@layout.registrationLayout
    displayMessage=!messagesPerField.existsError('password','password-confirm')
; section>

<#if section = "header">

    <div class="ecom-header">
        <h1 class="ecom-title">
            <#if isAppInitiatedAction??>
                Atualizar senha
            <#else>
                Nova senha
            </#if>
        </h1>
        <p class="ecom-subtitle">
            Defina uma nova senha para sua conta
        </p>
    </div>

<#elseif section = "form">

    <form
        id="kc-passwd-update-form"
        action="${url.loginAction}"
        method="post"
        class="ecom-form"
        novalidate
    >

        <div class="ecom-field">
            <label for="password-new">${msg("passwordNew")}</label>
            <input
                type="password"
                id="password-new"
                name="password-new"
                autofocus
                autocomplete="new-password"
                placeholder="Digite a nova senha"
                aria-invalid="<#if messagesPerField.existsError('password','password-confirm')>true</#if>"
            />

            <#if messagesPerField.existsError('password')>
                <span class="ecom-error" role="alert">
                    ${kcSanitize(messagesPerField.get('password'))?no_esc}
                </span>
            </#if>
        </div>

        <div class="ecom-field">
            <label for="password-confirm">${msg("passwordConfirm")}</label>
            <input
                type="password"
                id="password-confirm"
                name="password-confirm"
                autocomplete="new-password"
                placeholder="Repita a nova senha"
                aria-invalid="<#if messagesPerField.existsError('password-confirm')>true</#if>"
            />

            <#if messagesPerField.existsError('password-confirm')>
                <span class="ecom-error" role="alert">
                    ${kcSanitize(messagesPerField.get('password-confirm'))?no_esc}
                </span>
            </#if>
        </div>

        <#if isAppInitiatedAction??>
            <div class="ecom-checkbox">
                <input type="checkbox" id="logout-sessions" name="logout-sessions" value="on" checked />
                <label for="logout-sessions">
                    Encerrar sessão em outros dispositivos
                </label>
            </div>
        </#if>

        <div class="ecom-actions">
            <input
                type="submit"
                value="${msg('doSubmit')}"
                class="ecom-button"
            />

            <#if isAppInitiatedAction??>
                <input
                    type="submit"
                    name="cancel-aia"
                    value="${msg('doCancel')}"
                    class="ecom-button ecom-button--ghost"
                    formnovalidate
                />
            </#if>
        </div>

    </form>

</#if>

</@layout.registrationLayout>