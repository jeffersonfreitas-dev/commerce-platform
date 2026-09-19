<#macro registrationLayout displayMessage=false displayInfo=false displayRequiredFields=false>
<!DOCTYPE html>
<html lang="${.lang}">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="robots" content="noindex, nofollow">

    <title>${msg("loginTitle", (realm.displayName!''))}</title>

    <#if properties.favicon?has_content>
        <link rel="icon" href="${url.resourcesPath}/${properties.favicon}">
    </#if>

    <#if properties.styles?has_content>
        <#list properties.styles?split(' ') as style>
            <link href="${url.resourcesPath}/${style}" rel="stylesheet">
        </#list>
    </#if>

    <#if properties.scripts?has_content>
        <#list properties.scripts?split(' ') as script>
            <script src="${url.resourcesPath}/${script}" type="text/javascript"></script>
        </#list>
    </#if>
</head>
<body class="ecom-body">

    <#-- ============ NAVBAR ============ -->
    <header class="ecom-navbar">
        <div class="ecom-navbar__inner">
            <a href="${url.loginUrl!'#'}" class="ecom-navbar__brand">
                <img
                    src="${url.resourcesPath}/img/logo.svg"
                    alt="${realm.displayName!'Logo'}"
                    class="ecom-navbar__logo"
                />
                <span class="ecom-navbar__name">
                    ${realm.displayName!'Commerce'}
                </span>
            </a>
        </div>
    </header>

    <#-- ============ MAIN ============ -->
    <main class="ecom-main">
        <div class="ecom-card">

            <#-- Mensagens globais -->
            <#if displayMessage && message?has_content && (message.type != 'warning' || !isAppInitiatedAction??)>
                <div
                    class="ecom-alert ecom-alert--${message.type}"
                    role="alert"
                    aria-live="polite"
                >
                    <span class="ecom-alert__icon" aria-hidden="true">
                        <#if message.type = 'success'>✓
                        <#elseif message.type = 'warning'>⚠
                        <#elseif message.type = 'error'>✕
                        <#else>ℹ</#if>
                    </span>
                    <span class="ecom-alert__text">
                        ${kcSanitize(message.summary)?no_esc}
                    </span>
                </div>
            </#if>

            <#-- As três seções que as páginas podem preencher.
                 O corpo é executado UMA VEZ PARA CADA <#nested> abaixo. -->
            <#nested "header">
            <#nested "form">
            <#nested "info">
            <#nested "socialProviders">

        </div>
    </main>

    <#-- ============ FOOTER ============ -->
    <footer class="ecom-footer">
        <span>
            © ${.now?string("yyyy")} ${realm.displayName!'Commerce'}.
            Todos os direitos reservados.
        </span>
    </footer>

    <script>
        (function () {
            const firstField = document.querySelector(
                '.ecom-field input:not([type="hidden"]):not([type="checkbox"])'
            );
            if (firstField) firstField.focus();
        })();
    </script>
</body>
</html>
</#macro>