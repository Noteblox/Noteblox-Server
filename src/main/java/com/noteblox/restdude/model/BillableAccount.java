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
package com.noteblox.restdude.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.noteblox.restdude.model.enums.BillableAccountPlan;
import com.restdude.domain.users.model.User;
import com.restdude.mdd.annotation.model.ModelResource;
import com.restdude.mdd.model.AbstractPersistableModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;


/**
 * {@value #API_MODEL_DESCRIPTION}
 */
@Entity
@Table(name = "billable_account")
@ModelResource(pathFragment = BillableAccount.API_PATH_FRAGMENT,
        apiName = "Billable Accounts",
        apiDescription = "Website Operations")
@ApiModel(description = BillableAccount.API_MODEL_DESCRIPTION)
public class BillableAccount extends AbstractPersistableModel<String> {

    public static final String API_PATH_FRAGMENT = "billableAccounts";
    public static final String API_MODEL_DESCRIPTION = "A model representing a billable client, account.";

    @Id
    @Getter @Setter
    private String pk;

    @MapsId
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "contact", nullable = false, updatable = false, unique = true)
    @Getter @Setter
    private User contact;

    @Column(name = "plan", nullable = false)
    @Getter @Setter
    @ApiModelProperty(value = "The account purchased plan", required = true)
    private BillableAccountPlan plan = BillableAccountPlan.FREE;

    @JsonIgnore
    @OneToMany(mappedBy="billableAccount", fetch=FetchType.LAZY)
    @Getter @Setter
    private List<Website> sites = new ArrayList<Website>(0);

    public BillableAccount() {
    }

    @JsonIgnore
    @Override
    public boolean isNew() {
        return null == this.getPk();
    }

    @Override
    public void preSave() {
    }

    @Override
    public boolean equals(Object obj) {
        if (null == obj) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof BillableAccount)) {
            return false;
        }
        BillableAccount that = (BillableAccount) obj;
        return null == this.getPk() ? false : this.getPk().equals(that.getPk());
    }


}