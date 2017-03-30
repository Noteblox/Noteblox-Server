/**
 * This file is part of NoteBLOX.
 *
 * NoteBLOX is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * NoteBLOX is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with NoteBLOX.  If not, see <https://www.gnu.org/licenses/agpl-3.0.en.html>.
 */
define(['jquery', 'underscore', 'bloodhound', 'typeahead', "lib/restdudelib/util", "lib/restdudelib/form",
        "lib/restdudelib/uifield", "lib/restdudelib/backgrid", "lib/restdudelib/view", 'handlebars', "lib/restdudelib/models/Model"],
    function ($, _, Bloodhoud, Typeahead, Restdude, RestdudeForm, RestdudeField, RestdudeGrid, RestdudeView, Handlebars) {

        Restdude.model.WebsiteModel = Restdude.Model.extend(
            /** @lends Restdude.model.NoteModel.prototype */
            {
                toString: function () {
                    return this.get("title");
                }
            }, {
                // static members
                labelIcon: "fa fa-user fa-fw",
                public: true,
                pathFragment: "websites",
                typeName: "Restdude.model.WebsiteModel",
                menuConfig: {
                    rolesIncluded: ["ROLE_ADMIN", "ROLE_SITE_OPERATOR"],
                    rolesExcluded: null,
                },
                fields: {
                    title: {
                        fieldType: "String",
                        backgrid: {
                            cell: Restdude.components.backgrid.ViewRowCell,
                        }
                    },

                    description: {
                        fieldType: "String",
                    },
                    edit: {
                        fieldType: "Edit",
                    },
                },
            });

    });