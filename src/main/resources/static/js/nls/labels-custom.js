/*
 * 
 * To add a language create a new folder named using the two-letter 
 * ISO standard and add a labels.js without the "root" level, eg:
 * 
 * define({
 *     "tmpl" : {
 *    },
 *    "restdude" : {
 *    }
 * });
 * 
 */

define({
    "root": {

        "models": {
            "billableAccounts": {
                "singular": {
                    "label": "Client Account"
                },
                "plural": {
                    "label": "Client Accounts"
                },
            },
            "noteComments": {
                "singular": {
                    "label": "Note Comment"
                },
                "plural": {
                    "label": "Note Comments"
                },
            },
            "notes": {
                "singular": {
                    "label": "Note"
                },
                "plural": {
                    "label": "Notes"
                },
            },
            "websites": {
                "singular": {
                    "label": "Website"
                },
                "plural": {
                    "label": "Websites"
                },
            },
        },// models

    },
//	"de" : true,
});
