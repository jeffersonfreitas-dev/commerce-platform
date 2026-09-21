<#import "template.ftl" as layout>

<@layout.registrationLayout
    displayMessage=true
; section>

<#if section = "header">

    <div class="ecom-header">
        <h1 class="ecom-title">Criar conta</h1>
        <p class="ecom-subtitle">
            Preencha os dados abaixo para começar
        </p>
    </div>

<#elseif section = "form">

    <form
        action="${url.registrationAction}"
        method="post"
        class="ecom-form"
        novalidate
    >

        <#if !realm.registrationEmailAsUsername>
            <div class="ecom-field">
                <label for="username">${msg("username")}</label>
                <input
                    type="text"
                    id="username"
                    name="username"
                    value="${(register.formData.username!'')}"
                    autocomplete="username"
                    placeholder="Nome de usuário"
                />
                <#if messagesPerField.existsError('username')>
                    <span class="ecom-error" role="alert">
                        ${kcSanitize(messagesPerField.get('username'))?no_esc}
                    </span>
                </#if>
            </div>
        </#if>

        <div class="ecom-field">
            <label for="firstName">${msg("firstName")}</label>
            <input
                type="text"
                id="firstName"
                name="firstName"
                value="${(register.formData.firstName!'')}"
                autocomplete="given-name"
                placeholder="Seu nome"
            />
            <#if messagesPerField.existsError('firstName')>
                <span class="ecom-error" role="alert">
                    ${kcSanitize(messagesPerField.get('firstName'))?no_esc}
                </span>
            </#if>
        </div>

        <div class="ecom-field">
            <label for="lastName">${msg("lastName")}</label>
            <input
                type="text"
                id="lastName"
                name="lastName"
                value="${(register.formData.lastName!'')}"
                autocomplete="family-name"
                placeholder="Seu sobrenome"
            />
            <#if messagesPerField.existsError('lastName')>
                <span class="ecom-error" role="alert">
                    ${kcSanitize(messagesPerField.get('lastName'))?no_esc}
                </span>
            </#if>
        </div>

        <div class="ecom-field">
            <label for="email">${msg("email")}</label>
            <input
                type="email"
                id="email"
                name="email"
                value="${(register.formData.email!'')}"
                autocomplete="email"
                placeholder="seu@email.com"
            />
            <#if messagesPerField.existsError('email')>
                <span class="ecom-error" role="alert">
                    ${kcSanitize(messagesPerField.get('email'))?no_esc}
                </span>
            </#if>
        </div>

        <div class="ecom-field">
            <label for="password">${msg("password")}</label>
            <input
                type="password"
                id="password"
                name="password"
                autocomplete="new-password"
                placeholder="Crie uma senha forte"
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
                placeholder="Repita a senha"
            />
            <#if messagesPerField.existsError('password-confirm')>
                <span class="ecom-error" role="alert">
                    ${kcSanitize(messagesPerField.get('password-confirm'))?no_esc}
                </span>
            </#if>
        </div>

        <div class="ecom-actions">
            <input
                type="submit"
                value="${msg('doRegister')}"
                class="ecom-button"
            />
        </div>

    </form>

    <div class="ecom-register">
        <span>Já possui uma conta?</span>
        <a href="${url.loginUrl}" class="ecom-link">Entrar</a>
    </div>

</#if>

</@layout.registrationLayout>